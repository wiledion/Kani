/*
 * Copyright (c) 2012, Bonzi Wilédio
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 *    - Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 *    - Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *    - Neither the name of the copyright holder nor the names of its contributors 
 * may be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF 
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package kani.state;

import java.io.IOException;
import kani.data.AnimManager;
import kani.data.ImgManager;
import kani.data.MapManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class LoadState extends BasicGameState {

    int stateID = -1;
    ImgManager imgmanager;
    MapManager mapmanager;
    AnimManager animmanager;
    DeferredResource nextResource;
    boolean started;

    public    LoadState( int stateID )   {
        this.stateID = stateID;
        imgmanager = ImgManager.get();
        mapmanager = MapManager.get();
        animmanager = AnimManager.get();
    }


    public int getID() {
        return stateID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        LoadingList.setDeferredLoading(true);
        imgmanager.load();
        mapmanager.load();
        animmanager.load();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {

        if (nextResource != null) {
            gr.drawString("Loading: "+nextResource.getDescription(), 100, 100);
	}

	int total = LoadingList.get().getTotalResources();
	int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
        float bar = loaded / (float) total;
	gr.fillRect(100,550,loaded*40,20);
	gr.drawRect(100,550,total*40,20);

        if (started) {
            gr.drawString("LOADING COMPLETE",100,550);
            sbg.enterState(1);
	}

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (nextResource != null) {
            try {
                nextResource.load();
		// slow down loading for example purposes
		try { Thread.sleep(50); } catch (Exception e) {}
                } catch (IOException e) {
                                            throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
                                        }
                nextResource = null;
	}

	if (LoadingList.get().getRemainingResources() > 0) {
            nextResource = LoadingList.get().getNext();
	} else {
                if (!started) {
                    started = true;
		}
	}

    }

}
