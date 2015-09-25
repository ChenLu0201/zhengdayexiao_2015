describe('Home page', function() {
    it('should list three pets', function(done) {
        browser
             .url('http://localhost:8080')
             .elements('ul li').then(function(pets) {
                 expect(pets.value.length).toEqual(3);
              })
              .call(done);
    });
});