package com.soikea.hiplunch.provider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public abstract class BaseKimonoProvider extends BaseProvider {

    private static final String KIMONO_APIKEY = "88YhRC4s1GBK2FlBN32W9xOksgo01sHs";
    private static final String KIMONO_BASEURL = "https://www.kimonolabs.com/api/json/";
    private static final String KIMONO_BASEOPTS = "?apikey=";

    public final String KIMONO_URL = KIMONO_BASEURL + getKimonoApikey() + KIMONO_BASEOPTS + KIMONO_APIKEY;

    protected abstract String getKimonoApikey();

}
