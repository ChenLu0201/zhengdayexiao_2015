describe('pet controller', function(){
    var petController, scope = {}, location;
    var petService = {
        pet: function(){
            return {
                then: function(success){
                    success({id: 1, name: 'bingo'});
                }
            };
        }
    };

    beforeEach(module('app'));

    beforeEach(inject(function(_$controller_, _$injector_, $routeParams){
        petController = _$controller_('petController',
            {
                '$scope': scope,
                petService: petService,
                $routeParams: {id:'100'}
            }
        );
        location = _$injector_.get('$location');
    }));

    it('should assign returned pet to scope', function(){
        expect(scope.pet).toEqual({id: 1, name: 'bingo'});
    });

    it('should change path to pets when click back', function(){
        scope.back();

        expect(location.path()).toBe('/pets');
    });
});