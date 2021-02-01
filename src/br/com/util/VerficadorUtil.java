package br.com.util;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class VerficadorUtil {
    public static boolean estaNulo(Object objeto) {
        return objeto == null;
    }

    public static boolean naoEstaNulo(Object value) {
        return !estaNulo(value);
    }

    public static boolean estaVazio(Object objeto) {
        return objeto instanceof Collection ? ((Collection)objeto).isEmpty() : StringUtils.isEmpty(objeto.toString());
    }

    public static boolean estaNuloOuVazio(Object valor) {
        return estaNulo(valor) || estaVazio(valor);
    }

    public static boolean naoEstaNuloOuVazio(Object valor) {
        return !estaNuloOuVazio(valor);
    }
}
