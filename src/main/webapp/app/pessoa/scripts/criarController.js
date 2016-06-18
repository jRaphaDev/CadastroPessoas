
app.controller('criarController', ['$scope', 'pessoaFactory', function($scope, pessoaFactory) {

	$scope.model = {nome:'', cpf:'', idade:''};
	
	$scope.cadastrar = function() {
		pessoaFactory.criar($scope.model).then(function (a) {
			location.href = "#listar"
	     }).catch(function (a) {
	         alert(a);
	     });
	};
	
}]);
