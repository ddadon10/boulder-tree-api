# Boulder Tree Species API.
![Boulder, Colorado](https://github.com/dadon-david/boulder-tree-api/blob/master/src/main/webapp/assets/boulder.jpg?raw=true)

*This picture of [Boulder, Colorado](https://www.flickr.com/photos/43355249@N00/28695767093), by [Pedro Szekely](https://www.flickr.com/photos/pedrosz/) is licensed under [CC BY 2.0](https://creativecommons.org/licenses/by-sa/2.0/).*

# Get information about the Trees in Boulder, Colorado.
## The project is live! Visit [http://boulder.david.sh/](http://boulder.david.sh/) to use it!
## With this API you can:
#### • Query the different Trees, Species and Genus
#### • Filter by more than 10 properties like Leafcycle, Water Need, Dimension, Species, Genus etc.
#### • Fuzzy Search on some fields of the Tree resource
#### • Order by all the properties available
#### • Get the result in XML or JSON representation

*This API uses the Open Data provided by the City of Boulder, Colorado.*\
*You can find the original dataset and its resources [here](https://bouldercolorado.gov/open-data/public-tree-species/)*

## Data explanation
Here a description of the different resource of the API.
| Resource | Explanation                                                                                                                     | 
|----------|----------------------------------------------------------------------------------------------------------------------------------| 
| Tree     | A Tree represent an actual Tree in Boulder, CO. Each tree has many properties like name, species or genus.                       | 
| Genus    | Genus refers to a group of tree species that have fundamental traits in common but that differ in other, lesser characteristics. | 
| Species  | Species refers to a natural group of trees in the same genus made up of similar individuals.                                     | 

## Technical part
### Technical choices
- This project is an exercise, so some technical choices are a way for me to learn about a specific concept.
- I used Jersey, JAXB and Swagger.
- I wanted to be focus on the Java Logic, so I used a CSV and not a Database. Of course in general it's way better to use a DB.
- I am very careful about dependencies, it can quickly become a nightmare to manage. I don't reinvent the wheel, but I always double check before installing a deps.
### Technical Highlights
- Reflection is used to have a very lean `orderBy` - [See source code here](https://github.com/dadon-david/boulder-tree-api/blob/master/src/main/java/sh/david/bouldertreeapi/response/BaseResponse.java#L28)
- I wanted to practice serialization, so you can send a JSON or XML representation in a `GET` parameter and it will be deserialized automatically. I know it's a bit edgy but I had fun. Because the whole XML/JSON serialization revolve around JAXB, the solution is pretty lean - [See source code here](https://github.com/dadon-david/boulder-tree-api/blob/master/src/main/java/sh/david/bouldertreeapi/utils/Utils.java#L11)
- The Datastore heavily rely on the Stream API to load the data from the CSV - [See source code here](https://github.com/dadon-david/boulder-tree-api/blob/master/src/main/java/sh/david/bouldertreeapi/datastore/DataStore.java)

### Technical things to improve
- I had to create a `Response` class per resource because otherwise JAXB didn't find the class for unmarshalling because of generic type erasure and `@XmlSeeAlso` polluted my payload. I think it's possible to fix that by tuning JAXB - [See source code here](https://github.com/dadon-david/boulder-tree-api/blob/master/src/main/java/sh/david/bouldertreeapi/response/GenusResponse.java#L14)
- There is a lot of `if elseif` to manage the different parameters. I tried to use reflection to get them dynamically but then I lost all the type safety and the benefits of `@QueryParam`. Maybe there is a better way - [See source code here](https://github.com/dadon-david/boulder-tree-api/blob/master/src/main/java/sh/david/bouldertreeapi/resource/TreeResource.java)
- Unit testing
- Many other things

### Deployment
You need:
- Java 8
- A `3.x` servlet container. I used `tomcat 8.5`.
  - On tomcat, you need to add the attributes `relaxedPathChars='[]|' relaxedQueryChars='[]|{}^&#x5c;&#x60;&quot;&lt;&gt;'` to the `<Connector>` in `server.xml` because some clients don't URL-encode these characters. For more info see [this](https://tomcat.apache.org/tomcat-9.0-doc/config/http.html#Standard_Implementation:~:text=relaxedQueryChars) and [this](https://stackoverflow.com/a/50377112)
- To deploy just run `maven compile package` to get a `ROOT.war` and drop it into the servlet container!
