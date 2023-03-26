<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('commodity:brand:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('commodity:brand:delete')" type="danger" @click="deleteHandle()"
                   :disabled="dataListSelections.length <= 0">批量删除
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="id">
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        label="品牌名">
      </el-table-column>
      <el-table-column
        prop="logo"
        header-align="center"
        align="center"
        label="logo">
        <template slot-scope="scope">
          <img :src="scope.row.logo" style="height: 50px"/>
        </template>
      </el-table-column>
      <el-table-column
        prop="description"
        header-align="center"
        align="center"
        label="说明">
      </el-table-column>
      <el-table-column
        prop="isshow"
        header-align="center"
        align="center"
        label="显示">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isshow"
            active-color="#13ce66"
            :active-value="1"
            :inactive-value="0"
            inactive-color="#ff4949"
            @change="changeIsShow(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        prop="firstLetter"
        header-align="center"
        align="center"
        label="检索首字母">
      </el-table-column>
      <el-table-column
        prop="sort"
        header-align="center"
        align="center"
        label="排序">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="relateCategoryHandle(scope.row.id)">关联分类</el-button>
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>


    <el-dialog title="关联分类" :visible.sync="cateRelationDialogVisible" width="30%">
      <el-popover placement="right-end" v-model="popCatelogSelectVisible">
        <!-- <category-cascader :catelogPath.sync="catelogPath"></category-cascader>-->
        <!-- 这里我们加入分类的 Cascader 级联选择器, 前面我们使用过 -->
        <el-cascader
          v-model="cascadedCategoryId" :options="categories" :props="props"></el-cascader>
        <div style="text-align: right; margin: 0">
          <el-button size="mini" type="text" @click="popCatelogSelectVisible = false">取消</el-button>
          <el-button type="primary" size="mini" @click="addBrandCategoryRelation"> 确定</el-button>
        </div>
        <el-button slot="reference">新增关联</el-button>
      </el-popover>
      <el-table :data="cateRelationTableData" style="width: 100%">
        <el-table-column prop="id" label="#"></el-table-column>
        <el-table-column prop="brandName" label="品牌名"></el-table-column>
        <el-table-column prop="categoryName" label="分类名"></el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" label="操作">
          <template slot-scope="scope">
            <el-button
              type="text" size="small" @click="deleteCateRelationHandle(scope.row.id)">移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
<el-button @click="cateRelationDialogVisible = false">取 消</el-button>
<el-button type="primary" @click="cateRelationDialogVisible = false"> 确 定
</el-button>
</span>
    </el-dialog>
  </div>
</template>

<script>
import AddOrUpdate from './brand-add-or-update'

export default {
  data() {
    return {
      cateRelationDialogVisible: false,
      popCatelogSelectVisible: false,
      cateRelationTableData: [],

      cascadedCategoryId: [],
      categories: [],
      props: { //显示级联菜单的属性指定
        value: "id",
        label: "name",
        children: "childrenCategories"
      },
      brandId: 0,
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  activated() {
    this.getDataList()
  },
  methods: {
    deleteCateRelationHandle(id) {
      this.$confirm(`是否确认删除该分类?`, "提示", {
        confirmButtonText: "确定", cancelButtonText: "取消", type: "warning"
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/commodity/categorybrandrelation/delete'),
          method: 'post',
          data: this.$http.adornParams([id],false)
        }).then(({data}) => {
          this.getCategoryBrandRelation()
          this.$message({
            message: '操作成功',
            type: 'success',
          })
        })
      })
    },
    getCategoryBrandRelation() {
      this.$http({
        url: this.$http.adornUrl(`/commodity/categorybrandrelation/list/brandAllCategory/${this.brandId}`),
        method: "get",
      }).then(({data}) => {
        this.cateRelationTableData = data.data;
      });
    },
    addBrandCategoryRelation() {
      this.popCatelogSelectVisible = false
      this.$http({
        url: this.$http.adornUrl('/commodity/categorybrandrelation/saveAll'),
        method: 'post',
        data: this.$http.adornParams({
          'brandId': this.brandId,
          'categoryId': this.cascadedCategoryId[this.cascadedCategoryId.length - 1]
        })
      }).then(({data}) => {
        this.getCategoryBrandRelation()
        this.cascadedCategoryId = []
        if (data.code === 0) {
          this.$message({
            type: "success", message: "关联成功"
          });
        } else {
          this.$message({
            type: "error", message: "关联失败"
          });
        }
      })
    },
    relateCategoryHandle(id) {
      this.brandId = id
      this.getCategories()
      this.getCategoryBrandRelation()
      this.cateRelationDialogVisible = true
    },
    getCategories() {
      this.$http({
        url: this.$http.adornUrl('/commodity/category/list/tree'),
        method: "get"
      }).then(({data}) => {
        this.categories = data.tree;
      })
    },
    changeIsShow(row) {
      this.$http({
        url: this.$http.adornUrl('/commodity/brand/update/isshow'),
        method: 'post',
        data: this.$http.adornParams({
          'id': row.id,
          'isshow': row.isshow
        })
      }).then(({data}) => {
        if (data.code === 0) {
          this.$message({
            type: "success", message: "更新成功"
          });
        } else {
          this.$message({
            type: "success", message: "更新失败"
          });
        }
        this.getDataList();
      })
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/commodity/brand/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'name': this.dataForm.key
        })
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/commodity/brand/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    }
  }
}
</script>
