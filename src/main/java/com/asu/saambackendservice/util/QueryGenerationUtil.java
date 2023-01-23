package com.asu.saambackendservice.util;

public class QueryGenerationUtil {

    public static String generateArtWorkQuery(String name) {
        return " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX artist: <http://www.semanticweb.org/team24/artist#>\n" +
                "PREFIX artwork: <http://www.semanticweb.org/team24/artwork#>\n" +
                "PREFIX class: <http://www.semanticweb.org/team24/classification#>\n" +
                "SELECT DISTINCT ?x ?title ?artworkId  ?artistID ?des ?url ?classificationID ?classification ?subClassification ?medium \n" +
                "WHERE { ?x rdf:type artwork:Artwork .\n" +
                " ?x artwork:hasTitle ?title \n" +
                " FILTER(REGEX(?title,\"" + name + "\",\"i\"))\n" +
                " ?x artwork:hasArtistId ?artistID .\n" +
                " ?x artwork:hasArtworkId ?artworkId .\n" +
                "  ?x artwork:hasDescription ?des .\n" +
                "  ?x artwork:hasImageUrl ?url .\n" +
                "  ?y rdf:type class:Classification;\n" +
                "   class:hasClassificationID ?classificationID;\n" +
                "  class:hasClassification ?classification;\n" +
                " class:hasSubClassification ?subClassification;\n" +
                "class:hasMediums ?medium .\n" +
                "}LIMIT 10\n";
    }

    public static String generateArtistQuery(String name) {
        return "    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "    PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "    PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "    PREFIX artist: <http://www.semanticweb.org/project24/artist#>\n" +
                "    PREFIX artwork: <http://www.semanticweb.org/project24/artwork#>\n" +
                "    SELECT ?x ?artistName ?artistId ?nationalityId ?deathDate ?birthDate ?biography ?imageLink\n" +
                "    WHERE { ?x rdf:type artist:Artist .\n" +
                "            ?x artist:hasArtistName ?artistName .\n" +
                "            FILTER(regex(?artistName,\"" + name + "\", \"i\" ))\n" +
                "            ?x artist:hasArtistID ?artistId .\n" +
                "            ?x artist:hasNationalityID ?nationalityId .\n" +
                "            ?x artist:hasDeathDate ?deathDate .\n" +
                "            ?x artist:hasBirthDate ?birthDate .\n" +
                "            ?x artist:hasBiography ?biography .\n" +
                "    OPTIONAL {?x artist:hasImageLink ?imageLink}\n" +
                "    }LIMIT 10";
    }

}
