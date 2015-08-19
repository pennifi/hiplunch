package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.impl.MattilanniemiProvider;
import com.soikea.hiplunch.provider.impl.PiatoProvider;
import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19/08/14.
 */
public class Hiplunch {

    public static void main(String[] args) {

        HipChatter hipChatter = new HipChatter();

        MattilanniemiProvider mattilanniemiProvider = new MattilanniemiProvider();
        PiatoProvider piatoProvider = new PiatoProvider();
        WilhelmiinaProvider wilhelmiinaProvider = new WilhelmiinaProvider();

        hipChatter.sendMessage(mattilanniemiProvider.processMessage());
        hipChatter.sendMessage(wilhelmiinaProvider.processMessage());
        hipChatter.sendMessage(piatoProvider.processMessage());

    }
}
