<template>
  <div class="my-off-group">
    <div class="group-section">
      <h4 class="text-center">생성한 그룹</h4>
      <div v-if="this.$store.state.aoffGroupList.length !== 0">
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">제목</th>
              <th scope="col">방장</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(mygroup, index) in this.$store.state.aoffGroupList"
              :key="index"
            >
              <td scope="col">{{ index + 1 }}</td>
              <td>
                <router-link
                  :to="{
                    name: 'OffGroupDetail',
                    params: { group_id: mygroup.group_id },
                  }"
                  class="group-link"
                  >{{ mygroup.group_title }}</router-link
                >
              </td>
              <td>{{ mygroup.group_leader_user_id }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else class="text-center empty-message">
        생성한 그룹이 없습니다.
      </div>
    </div>
    <div class="group-section">
      <h4 class="text-center">참가한 그룹</h4>
      <div v-if="this.$store.state.boffGroupList.length !== 0">
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">제목</th>
              <th scope="col">방장</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(othergroup, index) in this.$store.state.boffGroupList"
              :key="index"
            >
              <td scope="col">{{ index + 1 }}</td>
              <td>
                <router-link
                  :to="{
                    name: 'OffGroupDetail',
                    params: { group_id: othergroup.group_id },
                  }"
                  class="group-link"
                  >{{ othergroup.group_title }}</router-link
                >
              </td>
              <td>{{ othergroup.group_leader_user_id }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else class="text-center empty-message">
        참가한 그룹이 없습니다.
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyOffGroup",
  methods: {},
};
</script>

<style scoped>
.table {
  width: 100%;
  border-collapse: collapse;
}

.table th,
.table td {
  padding: 8px;
  text-align: left;
}

.table th {
  background-color: #f2f2f2;
}

.table-striped tbody tr:nth-of-type(odd) {
  background-color: #f9f9f9;
}

.my-off-group {
  margin-top: 20px;
}

.group-section {
  margin-bottom: 30px;
}

.text-center {
  text-align: center;
}

.empty-message {
  color: #6c757d;
  font-weight: bold;
  font-size: 18px;
  margin-top: 20px;
}

.group-link {
  color: #007bff;
  text-decoration: none; /* Added underline effect on hover */
}

.group-link:hover {
  text-decoration: underline;
}
</style>
