var app = angular.module('app', ['ngRoute', 'ngCpfCnpj', 'ui.mask', 'ui.grid', 'ui.grid.selection', 'ui.grid.resizeColumns',  'ui.grid.treeView', 'ui.bootstrap']);

	app.config(function($routeProvider, $locationProvider) {
 
		$routeProvider
		//rota para listagem.
		.when('/listar', {
			templateUrl : 'app/pessoa/listar.html',
			controller     : 'listarController',
		//rota para criação.
		}).when('/criar', {
			templateUrl : 'app/pessoa/criar.html',
			controller  : 'criarController',
		//rota para edição.
		}).when('/editar/:id', {
			templateUrl : 'app/pessoa/editar.html',
			controller  : 'editarController',
		//caso rota seja desconhecida ele redireciona para a listagem.
		}).otherwise ({ redirectTo: '/listar' });
		
	});

	//filtro para mascara de cpf.
    app.filter('cpf', function () {
    	return function (input) {
    		var str = input + '';
			str = str.replace(/\D/g, '');
			str = str.replace(/(\d{3})(\d)/, '$1.$2');
			str = str.replace(/(\d{3})(\d)/, '$1.$2');
			str = str.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
			return str;
    	};
	});
    
    //diretiva responsavel em apenas deixar numeros serem inseridos no campo.
    app.directive('apenasNumeros', function(){
    	return {
    		require: 'ngModel',
    		link: function(scope, element, attrs, modelCtrl) {
    			modelCtrl.$parsers.push(function (inputValue) {
    				if (inputValue == undefined) return '' 
    				var transformedInput = inputValue.replace(/[^0-9]/g, ''); 
    				if (transformedInput!=inputValue) {
    					modelCtrl.$setViewValue(transformedInput);
    					modelCtrl.$render();
    				}
    				return transformedInput;
    			});
    		}
    	};
    });

    	