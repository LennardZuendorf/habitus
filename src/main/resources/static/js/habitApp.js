const app = Vue.createApp({});

app.component('habit_component', {
    template:
        `
          <!-- new habit form-->
          <div class="container-fluid">

              <!-- new habit form -->
              <div class="card habits-form-card">
                <div class="card-body">
                  <form id="newHabitForm">
                    <div class="row align-items-center justify-content-center">
    
                      <!-- tag input -->
                      <div class="col-lg-4">
                        <label class="form-label" for="newHabitTag" style>My new habit is:</label>
                        <input v-model="newHabitTag" type="text" id="newHabitTag" class="form-control mr-sm-2"/>
                      </div>
                      <!-- tag input -->
    
                      <!-- frequency input -->
                      <div class="col-lg-2">
                        <label class="form-label" for="newHabitFrequency" style>I want to do it:</label>
                        <select v-model="newHabitFrequency" id="newHabitFrequency" class="form-select mr-sm-2">
                          <option value="DAILY">daily</option>
                          <option value="WEEKLY">weekly</option>
                          <option value="BIWEEKLY">biweekly</option>
                          <option value="TRIWEEKLY">triweekly</option>
                          <option value="MONTHLY">monthly</option>
                        </select>
                      </div>
                      <!-- frequency input -->
    
                      <!-- quantity input -->
                      <div class="col-lg-2">
                        <label class="form-label" for="newHabitQuantity" style>In a quantity of:</label>
                        <input v-model="newHabitQuantity" type="number" id="newHabitQuantity" class="form-control mr-sm-2"/>
                      </div>
                      <!-- quantity input -->
    
                      <!-- icon selection -->
                      <div class="col-lg-2" style="visibility: hidden">
                        <label class="form-label" for="newHabitIcon" style>Select your icon</label>
                        <select v-model="newHabitIcon" id="newHabitIcon" class="form-select mr-sm-2">
                          <option value="none"> no icon</option>
                          <option value="sport">sport</option>
                          <option value="study">study</option>
                          <option value="community">community</option>
                          <option value="mindfulness">mindfulness</option>
                        </select>
                      </div>
                      <!-- icon selection -->
    
                      <!-- submit button -->
                      <div class="col-lg-1">
                        <label class="form-label"></label>
                        <button type="button" class="btn btn-rounded habit-button" v-on:click="createHabit()" style="height: 3rem !important">
                          CREATE
                        </button>
                      </div>
                      <!-- submit button -->
                    </div>
                  </form>
                  <!-- new habit form -->
                </div>
              </div>

              <!-- update habit form -->
              <div class="card habits-form-card" style="visibility: collapse; display: none; padding-top:2%" id="habit-display-edit-form">
                <div class="card-body">
                  <form id="updateHabitForm">
                    <div class="row align-items-center justify-content-center">
                      <!-- tag input -->
                      <div class="col-lg-4">
                        <label class="form-label" for="updateHabitTag" style>Change the tag:</label>
                        <input v-model="updateHabitTag" :placeholder="[[updatingHabit.tag]]" type="text" id="updateHabitTag" class="form-control mr-sm-2"/>
                      </div>
                      <!-- tag input -->

                      <!-- frequency input -->
                      <div class="col-lg-2">
                        <label class="form-label" for="updateHabitFrequency" style>I want to do it:</label>
                        <select v-model="updateHabitFrequency" :placeholder="[[updatingHabit.frequency]]" id="updateHabitFrequency" class="form-select mr-sm-2">
                          <option value="DAILY">daily</option>
                          <option value="WEEKLY">weekly</option>
                          <option value="BIWEEKLY">biweekly</option>
                          <option value="TRIWEEKLY">triweekly</option>
                          <option value="MONTHLY">monthly</option>
                        </select>
                      </div>
                      <!-- frequency input -->

                      <!-- quantity input -->
                      <div class="col-lg-2">
                        <label class="form-label" for="updateHabitQuantity" style>In a quantity of:</label>
                        <input v-model="updateHabitQuantity" :placeholder="[[updatingHabit.quantity]]" type="number" id="updateHabitQuantity" class="form-control mr-sm-2"/>
                      </div>
                      <!-- quantity input -->

                      <!-- icon selection -->
                      <div class="col-lg-2" style="visibility: hidden">
                        <label class="form-label" for="updateHabitIcon" style>Select your icon</label>
                        <select v-model="updateHabitIcon" id="updateHabitIcon" class="form-select mr-sm-2">
                          <option value="none"> no icon</option>
                          <option value="sport">sport</option>
                          <option value="study">study</option>
                          <option value="community">community</option>
                          <option value="mindfulness">mindfulness</option>
                        </select>
                      </div>
                      <!-- icon selection -->

                      <!-- submit button -->
                      <div class="col-lg-1">
                        <button type="button" class="btn btn-rounded edit-btns" v-on:click="saveEditing(updatingHabit.hid)">SAVE</button>
                      </div>
                      <!-- submit button -->
                    </div>
                  </form>
                  <!-- new habit form -->
                </div>
              </div>
    
              <!-- habits cards -->
              <div class="row" style="padding-top: 5%">
                <div v-for="habit in habits">
                    <div class="card habits-list-card border"  v-if="!habit.done">
                      <div class="card-body">
                        <!--card title-->
                        <div class="card-title card-title-m roboto-bold">
                          <div class="habit-display-text" >{{ habit.tag }}</div>
                          <div class="habit-display-text">&nbsp - &nbsp{{ habit.tag }}</div>
                          <div class="habit-display-text">TO GO</div>
                        </div>
                        <!--card title-->
                        
                        <!--card text-->
                        <div class="card-text">
                              <!--habit information & buttons row-->
                              <div class="row justify-content-between align-items-center align-content-between">
                                
                                <div class="col-md-9 roboto-medium">
                                    <div class="habit-display-text"> Your doing &nbsp</div>
                                    <div class="habit-display-text roboto-black vue-input-style"> {{ habit.tag }} &nbsp </div>
                                    <div class="habit-display-text"> in a &nbsp </div>
                                    <div class="habit-display-text roboto-black vue-input-style "> {{ habit.frequency }} &nbsp </div>
                                    <div class="habit-display-text"> frequency, &nbsp </div>
                                    <div class="habit-display-text roboto-black vue-input-style"> {{ habit.quantity }} &nbsp </div>
                                    <div class="habit-display-text"> time(s) a period.</div>
                                </div>
        
                                <div class="col-md-3">
                                  <button type="button" id="edit-button " class="btn btn-rounded edit-btns" v-on:click="toggleEditing(habit.hid)">EDIT</button>
                                  <button type="button" id="delete-button" class="btn btn-rounded edit-btns" v-on:click="deleteHabit(habit.hid)">DELETE</button>
                                  <button type="button" id="done-button" class="btn btn-rounded habit-button" style="margin-left: 1rem !important;" v-on:click="doneHabit(habit.hid)">DONE</button>
                                </div>
                              </div>
                          <!--habit information row-->
                        </div>
                        <!--card text-->
                      </div>
                    </div>
                </div>
                <!--card text-->
              </div>
              <!-- habits cards -->
          </div>
        `,
    data() {
        let url
        let uid;
        let updatingHabit;
        return {
            habits: [],
            url: "/habits/",
            updatingHabit:{
                hid: 9999,
                frequency: "DAILY",
                quantity: 1,
                tag: "9999"
            },
            uid
            };
        },

    methods: {
        requestHabits(){
            let URL;
            URL = this.url + this.uid.toString();

            axios.get(URL)
                .then(response => {this.habits = response.data; console.log(response.data)});
        },

        createHabit(){
            let frequency= this.newHabitFrequency;
            let quantity= this.newHabitQuantity;
            let tag= this.newHabitTag;
            let uid= this.uid;

            let result;
            let URL;
            URL = this.url;

            axios.post(URL,{
                done: false,
                doneAmount: 0,
                frequency: frequency,
                quantity: quantity,
                tag: tag,
                uid: uid
            })
                .then(response => {
                    result = response.data;
                    console.log("new habit created successfully");
                    this.requestHabits(this.uid);
                });


        },

        deleteHabit(hidInput){
            let URL;
            URL = this.url + hidInput.toString();
            console.log(URL)

            axios.delete(URL)
                .then(response => {
                    this.habits = response.data;
                    console.log(response.data)
                    this.requestHabits(this.uid);
                });
        },

        doneHabit(hidInput){
            const habit = this.habits.find(element => element.hid = hidInput);
            let URL;
            URL = this.url + hidInput.toString();
            let newAmount = habit.doneAmount+1;

            axios.put(URL, {
                done: habit.done,
                doneAmount: newAmount,
                frequency: habit.frequency,
                quantity: habit.quantity,
                tag: habit.tag,
                uid: habit.uid
            })
                .then(response => {
                    console.log("habit successfully marked as done");
                    this.requestHabits(this.uid);
                });
        },

        updateHabit(hidInput){
            const habit = this.habits.find(element => element.hid = hidInput);
            let URL;
            URL = this.url + hidInput.toString();

            axios.put(URL, {
                done: habit.done,
                doneAmount: habit.doneAmount,
                frequency: updateFrequency,
                quantity: updateQuantity,
                tag: updateTag,
                uid: this.uid
            })
                .then(response => {
                    result = response.data;
                    console.log("habit updated successfully");
                    this.requestHabits(this.uid);
                });

        },

        saveEditing(hidInput){
            this.updateHabit(hidInput);
            this.toggleEditing(hidInput);
        },

        toggleEditing(hidInput){
            const x = document.getElementById("habit-display-edit-form");
            this.setUpdateHabit(hidInput);

            console.log(x)
            console.log("Hello!")

            x.style.visibility = "visible";
            x.style.display = "block";
        },

        getUser(){
            this.uid = '999';
        },

        setUpdateHabit(hidInput){
            const habit = this.habits.find(element => element.hid = hidInput);

            this.updatingHabit.hid = hidInput;
            this.updatingHabit.tag = habit.tag;
            this.updatingHabit.frequency = habit.frequency;
            this.updatingHabit.quantity = habit.quantity;
            },
    },

    mounted: function() {
        this.getUser();
        this.requestHabits()
    }
});

app.mount('#habitApp');
