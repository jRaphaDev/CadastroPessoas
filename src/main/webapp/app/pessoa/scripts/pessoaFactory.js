app.factory('pessoaFactory', function($http) {

        var pessoaFactory = {};
        var url = "/CadastroPessoas/rest/pessoa";

        pessoaFactory.criar = function (pessoa) {
            var promisse = $http.post(url, pessoa).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        pessoaFactory.proucurar = function (id) {
            id = parseInt(id);
            var promisse = $http.get(url+"/"+id).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        pessoaFactory.modificar = function (id, pessoa) {
            id = parseInt(id);
            var promisse = $http.put(url+"/"+id, pessoa).success(function (response, data) {
                return {status:204};
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        pessoaFactory.deletar = function (id) {
            id = parseInt(id);
            var promisse = $http['delete'](url+"/"+id).success(function (response, data) {
                return {status:204};
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        };

        pessoaFactory.listar = function () {
            var promisse = $http.get(url).success(function (response, data) {
                return response.data;
            }).error(function (data, status) {
                return status;
            });
            return promisse;
        }


        return pessoaFactory;

    });
