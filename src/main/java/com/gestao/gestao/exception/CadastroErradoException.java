package com.gestao.gestao.exception;

public class CadastroErradoException extends Exception{
   public CadastroErradoException(){
       super("Informações inválidas para concluir o cadastro.");
   }
}
