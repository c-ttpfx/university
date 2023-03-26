<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="组名" prop="name">
      <el-input v-model="dataForm.name" placeholder="组名"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
    </el-form-item>
    <el-form-item label="说明" prop="description">
      <el-input v-model="dataForm.description" placeholder="说明"></el-input>
    </el-form-item>
    <el-form-item label="组图标" prop="icon">
      <el-input v-model="dataForm.icon" placeholder="组图标"></el-input>
    </el-form-item>
    <el-form-item label="所属分类id"  prop="categoryId" >
<!--      <el-input v-model="dataForm.categoryId" placeholder="所属分类 id"></el-input>-->

      <!--老师解读
      1. 在添加选择所属分类 ID 时，我们使用了 Cascader 级联选择器, 具体用法参考
      elementui-Cascader 级联选择器-基础用法
      2. v-model="cascadedCategoryId" : 是最终绑定的值,因为 el-cascader 关联的
      v-model 是一个数组，
      记录的是三级分类全部 id,即[第 1 级分类 id,第 2 级分类 id,第 3 级分类 id], 比如
      [1,4,321]因此在数据池的 categoryId 是一个数组
      3. :options="categorys" 表示级联显示的时候各个层的选型是从 categorys 数组来的
      4. :props="props" 显示的选项值(value)/显示的标签(label)/子选项(children) 分别和
      返回的 category 的对象的哪个字段关联
      5. -->
      <el-cascader
        filterable
        placeholder="搜索: "
        v-model="cascadedCategoryId"
        :options="categories"
        :props="props"
      />
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        categories: [], //层级关系的分类
        cascadedCategoryId: [],//[1,21,301]
        props: { //显示级联菜单的属性指定
          value: "id",
          label: "name",
          children: "childrenCategories"
        },
        dataForm: {
          id: 0,
          name: '',
          sort: '',
          description: '',
          icon: '',
          categoryId: 0,
        },
        dataRule: {
          name: [
            { required: true, message: '组名不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '说明不能为空', trigger: 'blur' }
          ],
          icon: [
            { required: true, message: '组图标不能为空', trigger: 'blur' }
          ],
          categoryId: [
            { required: true, message: '所属分类 id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      this.getCategories();
    },
    methods: {
      getCategories() {
        this.$http({
          url: this.$http.adornUrl('/commodity/category/list/tree'),
          method: "get"
        }).then(({data}) => {
          this.categories = data.tree;
        })
      },
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.cascadedCategoryId=[]
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()

          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/commodity/attrgroup/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.attrgroup.name
                this.dataForm.sort = data.attrgroup.sort
                this.dataForm.description = data.attrgroup.description
                this.dataForm.icon = data.attrgroup.icon
                this.dataForm.categoryId = data.attrgroup.categoryId
                this.cascadedCategoryId = data.attrgroup.cascadedCategoryId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/commodity/attrgroup/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'sort': this.dataForm.sort,
                'description': this.dataForm.description,
                'icon': this.dataForm.icon,
                // 'categoryId': this.dataForm.categoryId
                //数组的最后一个值就是该属性分组所在的第 3 级分类 id
                'categoryId': this.cascadedCategoryId[this.cascadedCategoryId.length -1]
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
