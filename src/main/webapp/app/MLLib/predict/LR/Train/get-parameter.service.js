/**
 * Created by Zhaoxuan on 2017/7/13.
 */
(function () {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .factory('GetParameter', GetParameter);

    GetParameter.$inject = ['$resource'];

    function GetParameter ($resource) {
        var service = $resource('api/getHdfsData', {}, {
            'get': {method: 'GET', params:{}, isArray: true,
                responseType: "text"
            }

        });

        return service;
    }
})();