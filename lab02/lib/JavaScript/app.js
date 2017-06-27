angular.module("toDoList", []);

angular.module("toDoList").controller("toDoListController", function ($scope) {
    $scope.app = "To Do List";

    $scope.tasks = [
        {name: "Terminar o lab02", date: "02/12/2016", time: "22:50", done: false, deleted: false},
        {name: "Estudar pra prova", date: "03/12/2016", time: "22:51", done: false, deleted: false},
        {name: "Fazer o jantar", date: "23/12/2016", time: "13:52", done: false, deleted: false}

    ];

    $scope.addTask = function (task) {

        this.task.done = false;
        this.deleted = false;
        $scope.tasks.push(angular.copy(task));
        delete $scope.task;
        
    };
    
    $scope.removeTask = function (tasks) {
        $scope.tasks = tasks.filter(function (task) {
            if(!task.deleted){
                return task;
            }
            
        })

    };

    $scope.calculatePercentage = function (tasks) {
        var sum = 0;

        tasks.forEach(function (task) {
            if (task.done){
                sum += 1;
            }
        });

        var percent = 0;

        if (tasks.length > 0){
            percent = (sum/tasks.length) * 100
        }

        return Math.floor(percent)
    };

    $scope.isTaskSelected = function (tasks) {
        return tasks.some(function (task) {
            return task.deleted;
        })

    };

    $scope.clearTasks = function () {
        $scope.tasks = [];

    }

});