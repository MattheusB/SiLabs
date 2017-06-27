app.controller("taskListController", function($scope, $http) {

	$scope.app = "To Do List";

	$scope.taskListSelected = {
		id : null,
		nome : "Task List",
		tarefas : []
	};

	$scope.priorities = [];

	$scope.tableFilter = [ {
		filter : "All"
	}, {
		filter : "Complete"
	}, {
		filter : "Not Complete"
	} ];

	$scope.categories = [];

	$scope.taskList = [];

	$scope.task = {};

	$scope.MAX_PERCENTUAL = 100;

	$scope.modeEdition = false;

	$scope.orderBy = "ordenar";

	$scope.order = null;

	$scope.addTask = function(task) {

		task.done = false;

		$scope.saveTask(task);

		delete $scope.Task;
	}

	function verifySubTask(tarefa) {

		var complete = 0;

		for (var i = 0; i < task.subTasks.length; i++) {

			if (task.subTasks[i].done) {
				complete++;
			}
		}

		if (complete == task.subTasks.length) {
			task.done = true;
			$scope.editTask(task);
		} else {
			task.done = false;
			$scope.editTask(task);
		}
	}

	$scope.remove = function(task) {

		var index = getIndexTask(task);

		$scope.taskListSelected.tasks.splice(index, 1);

		$scope.removeTask(task);
	}

	$scope.clearTasks = function() {

		$scope.taskListSelected.tasks = [];

		$scope.removeAllTasks($scope.taskLissSelected);
	}

	$scope.editTask = function() {
		$scope.mdoeEdition = true;
	}

	$scope.cancelEdition = function() {
		$scope.modeEdition = false;
	}

	$scope.saveEdition = function(task) {

		$scope.modeEdition = false;
		$scope.editTask(task);
		closeWindow();
	}

	$scope.calculatePercent = function(tasks) {

		var sum = 0;

		tasks.forEach(function(task) {

			if (task.done) {
				sum += 1;
			}
		});

		var percent = 0;

		if (tasks.length > 0) {
			percent = (sum / tasks.length) * 100;
		}

		return Math.floor(percent);
	}

	$scope.tasksFilter = function(select) {

		if (select == null || select.filter == "All")
			return '';

		if (select.filter == "Complete")
			return true;
		else if (select.filter == "Not Complete")
			return false;
	}

	$scope.categoriesFilter = function(category) {

		if (category == null || categoria == "All")
			return '';
		else
			return category;

	}

	$scope.makeTaskDone = function(task) {

		if (task.done) {
			task.done = false;
			$scope.makeSubTaskDone(task, false);
		} else {
			task.done = true;
			$scope.makeSubTaskDone(task, true);
		}

		$scope.editTask(task);
	}

	$scope.makeSubTaskDone = function(task, status) {

		for (var i = 0; i < task.subTasks.length; i++) {

			task.subTasks[i].done = status;
		}
	}

	var getIndexTask = function(task) {

		return $scope.taskListSelected.tasks.indexOf(task);
	}

	var view = document.getElementById("task-view");

	var close = document.getElementsByClassName("close")[0];

	close.onclick = function() {
		closeWindow();
	};

	window.onclick = function(event) {

		if (event.target == view) {

			closeWindow();
		}
	}

	function closeWindow() {
		view.style.display = "none";
	}

	$scope.openTask = function(task) {

		$scope.task = task;
		view.style.display = "block";
	}

	$http({
		method : "GET",
		url : "http://localhost:8080/taskList"
	}).then(function(response) {

		$scope.taskList = response.data;

	}, function(response) {
		console.log(response.data);
		console.log(response.status);
	});

	$scope.saveTask = function(tarefa) {
		$http({
			method : "POST",
			url : "http://localhost:8080/tasks",
			data : task
		}).then(
				function(response) {

					$scope.taskListSelected.tasks.push(response.data);

					if ($scope.categories.indexOf(response.data.category) == -1
							&& response.data.category !== null) {
						$scope.categories.push(response.data.category);
					} else if (response.data.category === null) {
						response.data.category = '';
					}

				}, function(response) {
					console.log(response.data);
					console.log(response.status);
				})
	}

	$scope.removeTask = function(task) {
		$http({
			method : "DELETE",
			url : 'http://localhost:8080/tasks/' + task.id
		}).then(function(response) {

			console.log(response.status);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.editTask = function(task) {
		$http({
			method : "PUT",
			url : "http://localhost:8080/tasks",
			data : task
		}).then(function(response) {

			console.log(response.status);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.removeAllTasks = function(taskList) {
		$http({
			method : "DELETE",
			url : "http://localhost:8080/tasks"
		}).then(function(response) {

			console.log(response.status);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.addTaskList = function(taskList) {
		$http({
			method : "POST",
			url : "http://localhost:8080/taskList",
			data : listaDeTarefas
		}).then(function(response) {

			$scope.taskListSelected = response.data;
			$scope.taskList.push(response.data);
			delete $scope.taskList;

			console.log(response.status);
			console.log(response.data);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.addSubTask = function(task, subTask) {

		subtarefa.done = false;

		$http({
			method : "POST",
			url : "http://localhost:8080/subTasks",
			data : subtarefa
		}).then(function(response) {

			tarefa.subTasks.push(response.data);
			delete $scope.subTask;

			console.log(response.status);
			console.log(response.data);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.deleteTaskList = function(taskList) {
		$http({
			method : "DELETE",
			url : "http://localhost:8080/listas/" + taskList.id
		}).then(function(response) {

			var index = $scope.taskList.indexOf(taskList);

			$scope.taskList.splice(index, 1);
			$scope.taskListSelected = {
				name : "To Do List",
				tasks : []
			};

			console.log(response.status);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.deleteAllTaskList = function() {
		$http({
			method : "DELETE",
			url : 'http://localhost:8080/taskList'
		}).then(function(response) {

			$scope.taskList = [];
			$scope.taskListSelected = {
				nome : "To Do List",
				tasks : []
			};

			console.log(response.status);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.editTitleList = function(newTitle) {
		$http({
			method : "PUT",
			url : "http://localhost:8080/taskList/"
		}).then(function(response) {

			$scope.listaDeTarefasSelecionada.title = response.data.title;

			console.log(response.status);

			delete $scope.newTiel;

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.editSubTask = function(task, subTask) {
		$http({
			method : "PUT",
			url : "http://localhost:8080/subTasks",
			data : subTask
		}).then(function(response) {

			verifySubTask(tarefa);
			console.log(response.status);
			console.log(response.data);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	$scope.deleteSubTask = function(task, subTask) {
		$http({
			method : "DELETE",
			url : "http://localhost:8080/subTasks"
		}).then(function(response) {

			var index = task.subTask.indexOf(subTask);

			task.subTask.splice(index, 1);

			verifySubTask(tarefa);
			console.log(response.status);

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		})
	}

	function ordenarPorPrioridade(value1, value2) {

		if (value1.value === "HIGH")
			return -1;
		else if (value2.value === "HIGH")
			return 1;
		else if (value1.value === "MIDDLE")
			return -1;
		else if (value2.value === "MIDDLE")
			return 1;
		else
			return -1;
	}

	$scope.selectOrder = function() {

		if ($scope.orderBy === "priority") {
			$scope.order = orderByPriority;
		} else if ($scope.orderBy === "description") {
			$scope.order = null;
		} else {
			$scope.order = null;
		}
	}

	$scope.saveCategories = function() {

		var categories = [ "All" ];

		for (var i = 0; i < $scope.taskListSelected.tasks.length; i++) {

			var category = $scope.taskListSelected.tasks[i].category;

			if ((categories.indexOf(category) === -1) && (category != null)) {
				categories.push(category);
			}
		}

		$scope.categories = categories;
	}

	$scope.calculateFilter = function(category, result) {
		var amount = 0;

		if (typeof category !== 'undefined' && category !== 'All') {

			for (var i = 0; i < $scope.taskListSelected.tasks.length; i++) {

				var task = $scope.taskListSeleceted.tasks[i];

				if (task.category === category && task.done === result) {
					amount++;
				}

			}
		} else {

			for (var i = 0; i < $scope.taskListSelected.tasks.length; i++) {

				var task = $scope.taskListSelected.tasks[i];

				if (task.done === result) {
					amount++;
				}
			}
		}

		return amount;
	}

	$scope.taskUnfinished = function() {

		var amount = 0;

		for (var i = 0; i < $scope.taskList.length; i++) {

			var taskList = $scope.taskList[i];

			for (var j = 0; j < taskList.tasks.length; j++) {

				var task = taskList.tasks[j];

				if (task.done === false) {
					amount++;
				}
			}
		}

		return amount;
	}

});