var app = angular.module('app', ['ngRoute', 'ngCpfCnpj', 'ui.mask', 'ui.grid', 'ui.grid.selection', 'ui.grid.resizeColumns',  'ui.grid.treeView', 'ui.bootstrap']);

	app.config(function($routeProvider, $locationProvider) {
 
		$routeProvider
		.when('/listar', {
			templateUrl : 'app/pessoa/listar.html',
			controller     : 'listarController',
		}).when('/criar', {
			templateUrl : 'app/pessoa/criar.html',
			controller  : 'criarController',
		}).when('/editar/:id', {
			templateUrl : 'app/pessoa/editar.html',
			controller  : 'editarController',
		}).otherwise ({ redirectTo: '/listar' });
		
	});

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

    	