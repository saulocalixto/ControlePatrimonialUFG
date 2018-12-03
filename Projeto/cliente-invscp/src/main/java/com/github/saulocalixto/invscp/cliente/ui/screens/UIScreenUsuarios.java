/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.InventoryAPI;
import com.github.saulocalixto.invscp.cliente.api.UsuarioAPI;
import com.github.saulocalixto.invscp.cliente.core.Usuario;
import com.github.saulocalixto.invscp.cliente.ui.IO;
import com.github.saulocalixto.invscp.cliente.ui.UIScreen;
import com.github.saulocalixto.invscp.cliente.ui.UIScreenOption;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Lucas Sampaio Dias
 */
public class UIScreenUsuarios extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            try {
                visualizarUsuario();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Editar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
        opcoes.put(4, new UIScreenOption("Deletar", () -> {
            deletarUsuario();
        }));
    }
    
    private static void cadastrarUsuario() {
        
    }

    private static void visualizarUsuario() throws IOException {
        final String email = IO.readString("Insira o e-mail do usuario:");
        final String json = UsuarioAPI.getUsuario(email);

        JSONObject obj = new JSONObject(json);
        System.out.println("\nNome: " + obj.get("nome"));
        System.out.println("Grupo: " + obj.get("grupo"));
        
        JSONObject depto = obj.getJSONObject("departamento");
        System.out.println("Departamento: " + 
                depto.getString("nomeDoDepartamento"));
    }
    
    private static void editarUsuario() {
        
    }
    
    private static void deletarUsuario() {
        final String email;
        try {
            email = IO.readString("Insira o e-mail do usuario:");
            UsuarioAPI.deletarUsuario(email);
        } catch (Exception e) {
            System.out.println("Não foi possível deletar o usuário.");
        }
    }
    
    public UIScreenUsuarios() {
        super("Usuários", opcoes);
    }
    
}
