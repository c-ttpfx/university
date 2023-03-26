<template>
  <div>
    <el-button type="danger" @click="batchDelete">批量删除</el-button>
    <el-tree
      ref="categoryTree"
      :data="data"
      show-checkbox
      node-key="id"
      :props="defaultProps"
      :default-expanded-keys="expandedKey"
      :expand-on-click-node="false">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)">
            Append
          </el-button>
          <el-button
            v-if="data.childrenCategories === null"
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            Delete
          </el-button>
           <el-button
             type="text"
             size="mini"
             @click="() => edit(data)">
            edit
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog title="分类信息" :visible.sync="dialogVisible" width="30%">
      <el-form :model="category">
        <el-form-item label="分类名">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标" v-if="dialogType==='update'">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="单位" v-if="dialogType==='update'">
          <el-input v-model="category.proUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: [],
      expandedKey: [],
      defaultProps: {
        children: 'childrenCategories',
        label: 'name'
      },
      category: {
        name: "",
        parentId: 0,
        catLevel: 0,
        isShow: 1,
        sort: 0,
        proCount: 0,
        proUnit: "",
        icon: "",
        id: null
      },
      dialogVisible: false,
      dialogType: ''
    };
  },
  methods: {
    batchDelete() {
      let checkedNodes = this.$refs.categoryTree.getCheckedNodes();
      console.log("被选中的分类元素= ", checkedNodes);
      let ids = []
      let names = []
      for (let i = 0; i < checkedNodes.length; i++) {
        ids.push(checkedNodes[i].id);
        names.push(checkedNodes[i].name);
      }
      this.$confirm(`是否批量删除【${names}】菜单?`, "提示", {
        confirmButtonText: "确定", cancelButtonText: "取消", type: "warning"
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/commodity/category/delete'),
          method: "post",
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          this.$message({
            message: "家居分类批量删除 OK", type: "success"
          });
          this.tree();
        });
      });
    },
    addOrUpdate() {
      if (this.dialogType === 'add') this.append()
      else if (this.dialogType === 'update') this.update()
    },
    update() {
      let {id, name, icon, proUnit} = this.category
      this.$http({
        url: this.$http.adornUrl('/commodity/category/update'),
        method: 'post',
        data: this.$http.adornParams({id, name, icon, proUnit}, false)
      }).then(({data}) => {
        this.$message({
          message: '操作成功',
          type: 'success',
        })
        this.tree()
        this.dialogVisible = false
        this.expandedKey = [this.category.parentId];
      })
    },
    edit(data) {
      this.$http({
        url: this.$http.adornUrl(`/commodity/category/info/${data.id}`),
        method: 'get',
      }).then(({data}) => {
        this.category = data.category;

        this.dialogType = 'update'
        this.dialogVisible = true
      })
    },
    append(data) {
      this.dialogType = 'add'
      this.dialogVisible = true;
      this.category.parentId = data.id;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.id = null;
      this.category.name = "";
      this.category.icon = "";
      this.category.proUnit = "";
      this.category.proCount = 0;
      this.category.sort = 0;
      this.category.isShow = 0;
    },
    addCategory() {
      this.$http({
        url: this.$http.adornUrl('/commodity/category/save'),
        method: 'post',
        data: this.$http.adornParams(this.category, false)
      }).then(({data}) => {
        this.$message({
          message: '操作成功',
          type: 'success',
        })
        this.tree()
        this.dialogVisible = false
        this.expandedKey = [this.category.parentId];
      })
    },
    remove(node, data) {
      console.log("node->", node, "-----data->", data)
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定", cancelButtonText: "取消", type: "warning"
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/commodity/category/delete'),
          method: 'post',
          data: this.$http.adornParams([data.id], false)
        }).then(({data}) => {
          this.$message({
            message: '操作成功',
            type: 'success',
          })
          this.tree()
          this.expandedKey = [node.parent.data.id]
        })
      })

    },
    tree() {
      this.$http({
        url: this.$http.adornUrl('/commodity/category/list/tree'),
        method: 'get',
      }).then(({data}) => {
        this.data = data.tree;
      })
    }
  },
  created() {
    this.tree();
  }
};
</script>

<style scoped>

</style>
