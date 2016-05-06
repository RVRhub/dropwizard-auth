module.exports = function(grunt) {
    grunt.initConfig({
        bower_concat: {
            all: {
                dest: '../scripts/scripts.js',
                cssDest: '../styles/styles.css',
                mainFiles: {
                    'bootstrap': ['dist/css/bootstrap.css']
                }
            }
        }

    });
    grunt.loadNpmTasks('grunt-bower-concat');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.registerTask('default', ['bower_concat']);
};