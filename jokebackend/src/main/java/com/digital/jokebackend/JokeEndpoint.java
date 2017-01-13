/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.digital.jokebackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.digital.jokelib.Joker;

@Api(
    name = "jokeApi",
    version = "v1",
    namespace = @ApiNamespace(
            ownerDomain = "jokebackend.digital.com",
            ownerName = "jokebackend.digital.com",
            packagePath = ""
    )
)
public class JokeEndpoint {
    @ApiMethod(name = "joke")
    public Joker joke() {
        return new Joker();
    }

}
