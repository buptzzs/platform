(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$location'];

    function HomeController ($scope, Principal, LoginService, $state,$location) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });
        $scope.report = "http://"+$location.host()+":8085/report/main";
        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        function jumpReport () {

        }
    }
})();
