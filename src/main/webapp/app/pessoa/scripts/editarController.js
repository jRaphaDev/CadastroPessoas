
app.controller('editarController', ['$scope', '$routeParams', 'pessoaFactory', function($scope, $routeParams, pessoaFactory) {

	$scope.model = {id:'', nome:'', cpf:'', idade:''};
	
	$scope.buscarPessoa = function() {
		pessoaFactory.proucurar($routeParams.id).then(function (a) {
			$scope.model = a.data;
		}).catch(function (a) {
	         alert(a);
	    });
	};
	
	$scope.modificar = function() {
		pessoaFactory.modificar($routeParams.id, $scope.model).then(function (a) {
			location.href = "#listar";
		}).catch(function (a) {
	         alert(a);
	    });
	};
	
	$scope.buscarPessoa();
       
}]);
