<template>
  <tr>
    <th scope="row">{{ no }}</th>
    <td>
      <router-link :to="`ongroup/detail/${group_id}`">{{
        group_title
      }}</router-link>
    </td>
    <td>{{ group_message }}</td>
    <td>{{ group_leader_user_id }}</td>
    <td>{{ group_cnt }}</td>
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
    group_image: String,
    group_orgimage: String,
  },
  methods: {},
};
</script>

<style></style>
