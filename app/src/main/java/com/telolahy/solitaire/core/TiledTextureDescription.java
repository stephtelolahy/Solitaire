package com.telolahy.solitaire.core;

import android.content.Context;

import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.debug.Debug;

import java.io.IOException;

/**
 * Created by stephanohuguestelolahy on 12/13/14.
 */
public class TiledTextureDescription {

    public ITiledTextureRegion textureRegion;

    private final String textureFile;
    private ITexture texture;
    private final int columns;
    private final int rows;

    public TiledTextureDescription(String file, int col, int row) {
        textureFile = file;
        columns = col;
        rows = row;
    }

    public void load(TextureManager textureManager, Context context) {
        try {
            texture = new AssetBitmapTexture(textureManager, context.getAssets(), textureFile, TextureOptions.BILINEAR);
            textureRegion = TextureRegionFactory.extractTiledFromTexture(texture, columns, rows);
            texture.load();
        } catch (IOException e) {
            texture = null;
            textureRegion = null;
            Debug.e(e);
        }
    }

    public void unload() {
        if (texture != null) {
            texture.unload();
        }
        textureRegion = null;
    }
}