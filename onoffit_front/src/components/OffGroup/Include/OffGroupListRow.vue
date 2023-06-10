<template>
  <tr class="table-row" @click="selectRow">
    <td>{{ no }}</td>
    <td class="group-title">
      <router-link :to="`offgroup/detail/${group_id}`" @click.stop>{{
        group_title
      }}</router-link>
    </td>
    <td class="group-message" @click.stop>{{ group_message }}</td>
    <td class="group-leader" @click.stop>{{ group_leader_user_id }}</td>
    <td class="group-count" @click.stop>{{ group_cnt }}</td>
  </tr>
</template>

<script>
export default {
  props: {
    no: String,
    group_id: Number,
    group_title: String,
    group_message: String,
    group_description: String,
    group_cnt: Number,
    group_leader_user_id: String,
    group_address: String,
    group_image: String,
    group_orgimage: String,
  },
  methods: {
    selectRow() {
      this.$router.push(`offgroup/detail/${this.group_id}`);
    },
  },
};
</script>

<style scoped>
.table-row {
  background-color: #f5f5f5;
  cursor: pointer;
}

td {
  padding: 10px;
  text-align: center;
}

.group-title {
  font-weight: bold;
}

.group-message {
  width: 300px;
}
</style>
