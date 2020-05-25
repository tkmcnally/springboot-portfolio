// Check that required variables exist
if( ( process.argv[2] == undefined ) || ( process.argv[3] == undefined ) ){
    console.log('Missing variables')
    console.log('1, local path to coverage file. 2, url to target css file')
    return;
}

(async () => {

    var fs = require("fs");
    // Get content from file
    var contents = fs.readFileSync( process.argv[2] );
    // Define to JSON type
    var jsonContent = JSON.parse(contents);

    const slices = [];

    for (i = 0; i < jsonContent.length; i++) {

        // console.log( jsonContent[i] );
        if( jsonContent[i].url == process.argv[3] ){

            console.log( 'Critical CSS from: ' + process.argv[3] );

            for (j = 0; j < jsonContent[i].ranges.length; j++) {
                slices.push( jsonContent[i].text.slice( jsonContent[i].ranges[j].start, jsonContent[i].ranges[j].end ));
            }
        }

    }

    console.log('------------------------------------------------')
    console.log(slices.join(''));
    console.log('------------------------------------------------')

})();