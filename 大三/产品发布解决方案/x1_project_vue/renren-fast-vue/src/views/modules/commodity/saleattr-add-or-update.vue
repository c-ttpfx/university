<template>
  <el-dialog
    :title="!dataForm.attrId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="120px">
      <el-form-item label="属性名" prop="attrName">
        <el-input v-model="dataForm.attrName" placeholder="属性名"></el-input>
      </el-form-item>

      <el-form-item label="图标" prop="icon">
        <el-input v-model="dataForm.icon" placeholder="图标"></el-input>
      </el-form-item>
      <el-form-item label="可选值列表" prop="valueSelect">
        <el-input v-model="dataForm.valueSelect" placeholder="可选值列表"></el-input>
      </el-form-item>
      <el-form-item label="属性类型" prop="attrType">
        <el-select disabled value="销售属性"/>
      </el-form-item>
      <el-form-item label="启用状态" prop="enable">
        <el-select v-model="dataForm.enable">
          <el-option label="显示" value="1" :key="1"/>
          <el-option label="不显示" value="0" :key="2"/>
        </el-select>
      </el-form-item>
      <el-form-item label="所属分类" prop="categoryId">
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
  data() {
    return {
      categories: [], //层级关系的分类
      cascadedCategoryId: [],//[1,21,301]
      props: { //显示级联菜单的属性指定
        value: "id",
        label: "name",
        children: "childrenCategories"
      },

      visible: false,
      dataForm: {
        attrId: 0,
        attrName: '',
        searchType: '',
        icon: '',
        valueSelect: '',
        attrType: 0,
        enable: '',
        categoryId: '',
        showDesc: '',
      },
      dataRule: {
        attrName: [
          {required: true, message: '属性名不能为空', trigger: 'blur'}
        ],
        searchType: [
          {required: true, message: '是否需要检索[0-不需要，1-需要]不能为空', trigger: 'blur'}
        ],
        icon: [
          {required: true, message: '图标不能为空', trigger: 'blur'}
        ],
        valueSelect: [
          {required: true, message: '可选值列表[用逗号分隔]不能为空', trigger: 'blur'}
        ],
        attrType: [
          {required: true, message: '属性类型[0-销售属性，1-基本属性]不能为空', trigger: 'blur'}
        ],
        enable: [
          {required: true, message: '启用状态[0 - 禁用，1 - 启用]不能为空', trigger: 'blur'}
        ],
        categoryId: [
          {required: true, message: '所属分类不能为空', trigger: 'blur'}
        ],
        showDesc: [
          {required: true, message: '快速展示【是否展示在介绍上；0-否 1-是】不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  watch: {
    cascadedCategoryId(path) {
      this.dataForm.categoryId = this.cascadedCategoryId[this.cascadedCategoryId.length - 1]
      this.dataForm.categoryId = path[path.length - 1];
    },
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
    init(id) {
      this.dataForm.attrId = id || 0
      this.visible = true
      this.getCategories()
      this.cascadedCategoryId = []
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.attrId) {
          this.$http({
            url: this.$http.adornUrl(`/commodity/attr/info/${this.dataForm.attrId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.attrName = data.attr.attrName
              this.dataForm.searchType = data.attr.searchType
              this.dataForm.icon = data.attr.icon
              this.dataForm.valueSelect = data.attr.valueSelect
              this.dataForm.attrType = data.attr.attrType
              this.dataForm.enable = data.attr.enable
              this.dataForm.categoryId = this.cascadedCategoryId[this.cascadedCategoryId.length - 1]
              this.dataForm.showDesc = data.attr.showDesc
              this.cascadedCategoryId = data.attr.cascadedCategoryId
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/commodity/attr/${!this.dataForm.attrId ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'attrId': this.dataForm.attrId || undefined,
              'attrName': this.dataForm.attrName,
              'searchType': this.dataForm.searchType,
              'icon': this.dataForm.icon,
              'valueSelect': this.dataForm.valueSelect,
              'attrType': this.dataForm.attrType,
              'enable': this.dataForm.enable,
              'categoryId': this.dataForm.categoryId,
              'showDesc': this.dataForm.showDesc,
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
