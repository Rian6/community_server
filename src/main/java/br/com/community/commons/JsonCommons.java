package br.com.community.commons;

import br.com.community.entity.Produto;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rodrigo
 */
public class JsonCommons {

    public List<Produto> converterJsonToList(String json) {

        Gson g = new GsonBuilder().create();

        Type collectionType = new TypeToken<List<Produto>>() {
        }.getType();
        List<Produto> lista = g.fromJson(json, collectionType);

        return lista;
    }

}
