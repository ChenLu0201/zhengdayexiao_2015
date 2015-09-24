describe('one test', function(){
    var petService, httpBackend;

    beforeEach(module('app'));

    beforeEach(inject(function(_$injector_){
        petService = _$injector_.get('petService');
        httpBackend = _$injector_.get('$httpBackend');
    }));

    it('should return pets', function(){
        var pets = [{"id": 1, "name": 'bingo'}];
        httpBackend.expectGET('/api/pets').respond(pets);

        petService.pets().then(function(response){
            expect(angular.equals(response, pets)).toBe(true);
        });
        httpBackend.flush();
    })

    it('should return specific pet', function(){
        var pet = {id: 1, name: 'bingo'};
        httpBackend.expectGET('/api/pets/1').respond(pet);

        petService.pet(1).then(function(response){
            expect(angular.equals(response, pet)).toBe(true);
        });
        httpBackend.flush();
    });
});