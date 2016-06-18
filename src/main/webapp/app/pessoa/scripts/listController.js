
app.controller('listarController', ['$scope', '$filter', 'pessoaFactory', function($scope, $filter, pessoaFactory) {

    $scope.barButton = false;

    //Grid's options definition.
    $scope.gridOptions = { 
        enableRowSelection: true, 
        enableRowHeaderSelection: false, 
        enableFiltering: false, 
        rowHeight: 25,
        useExternalFiltering: false,
        columnDefs : [
            { name: 'id', visible:false},
            { name: 'nome', width: '70%', enableSorting: true, resizable: true},
            { name: 'cpf', width: '20%', filter:'cpf', enableSorting: true, resizable: true},
            { name: 'idade', width: '10%', enableSorting: true, resizable: true},
        ]
    };

    $scope.gridOptions.multiSelect = false;
    $scope.gridOptions.modifierKeysToMultiSelect = false;
    $scope.gridOptions.noUnselect = false;

    $scope.gridOptions.onRegisterApi = function(gridApi) {
        //set gridApi on scope.
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope, function(row){
            $scope.barButton = (row.isSelected) ? true : false;
        });
    };

    $scope.toggleRowSelection = function() {
         $scope.gridApi.selection.clearSelectedRows();
         $scope.gridOptions.enableRowSelection = !$scope.gridOptions.enableRowSelection;
    };

    //Edit a specific product
    $scope.onEdit = function() {
        var list = $scope.gridApi.selection.getSelectedGridRows();
        location.href = '#editar/'+list[0].entity.id;
    }

    //Get id to delete a specific product 
    $scope.onRemove = function() {
        var list = $scope.gridApi.selection.getSelectedGridRows();
        $scope.remove(list[0].entity.id);
    }
    
  //Delete a specific product 
    $scope.remove = function(id) {
    	pessoaFactory.deletar(id).then(function (a) {
			$scope.loadPessoas();
		}).catch(function (a) {
	         alert(a)
	    });
    }
    
    $scope.loadPessoas = function() {
    	$scope.barButton = false;
    	pessoaFactory.listar().then(function (data) {
			
    		$scope.gridOptions.data = [];
			for (var x = 0; x < data.data.length; x++) {
				$scope.gridOptions.data.push({id:data.data[x].id, nome:data.data[x].nome, cpf:$filter('cpf')(data.data[x].cpf), idade:data.data[x].idade});
			}
			 
		}).catch(function (a) {
	         alert(a)
	    });
    }
    
    $scope.loadPessoas();
    
}]);
