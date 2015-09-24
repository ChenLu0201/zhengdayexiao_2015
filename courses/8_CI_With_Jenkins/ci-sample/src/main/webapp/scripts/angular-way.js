angular.module('app', ['ngResource', 'ngRoute']);

angular.module('app')
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/pets', {
                templateUrl: 'views/pet/pets.html',
                controller: 'petsController'
            })
            .when('/pets/new', {
                templateUrl: 'views/pet/new.html',
                controller: 'petController'
            })
            .when('/pets/:id', {
                templateUrl: 'views/pet/detail.html',
                controller: 'petController'
            })
            .when('/pets/:id/edit', {
                templateUrl: 'views/pet/edit.html',
                controller: 'petController'
            })
            .otherwise({
                redirectTo: '/pets'
            });
    }]);

angular.module('app')
    .controller('petsController', ['$scope', 'petService', '$location', function ($scope, petService, $location) {
        $scope.search = '';
        $scope.delete = function (id) {
            petService.deletePet(id).then(function (response) {
                listPets();
            });
        };
        $scope.edit = function (id) {
            $location.path('pets/' + id + '/edit');
        };
        listPets();

        function listPets() {
            petService.pets().then(function (response) {
                $scope.pets = response;
            });
        }

    }])
    .controller('petController', ['$scope', 'petService', '$routeParams', '$location', '$window', function ($scope, petService, $routeParams, $location, $window) {
        $scope.pet = {};
        if ($routeParams.id) {
            petService.pet($routeParams.id).then(function (response) {
                $scope.pet = response;
            });
        }
        $scope.update = function () {
            petService.updatePet($scope.pet);
            reloadPets();
        };
        $scope.new = function () {
            petService.addPet($scope.pet);
            reloadPets();
        };
        $scope.back = function (yourName) {
            reloadPets();
        };
        
        function reloadPets() {
            $location.path('pets');
            $window.location.reload();
        }
    }])
    .directive('petDetail', ['$location', function ($location) {
        return {
            restrict: 'E',
            scope: {
                pet: '=',
                backFunc: '&'
            },
            templateUrl: '/views/partials/pet-detail.html',
            link: function (scope) {

                scope.$watch(function () {
                    console.log('digesting...');
                });
            }
        };
    }])
    .factory('petService', ['$resource', function ($resource) {
        var entityPet = $resource('/api/pets/:petId', {petId: '@id'}, {
            'update': {method: 'PUT'}
        });

        return {
            pets: pets,
            pet: getPet,
            deletePet: deletePet,
            updatePet: updatePet,
            addPet: addPet
        };

        function pets() {
            return entityPet.query().$promise;
        }

        function getPet(id) {
            return entityPet.get({petId: id}).$promise;
        }

        function deletePet(id) {
            return entityPet.delete({petId: id}).$promise;
        }

        function updatePet(pet) {
            return entityPet.update({petId: pet.id}, pet).$promise;
        }

        function addPet(pet) {
            return entityPet.save(pet).$promise;
        }
    }]);
