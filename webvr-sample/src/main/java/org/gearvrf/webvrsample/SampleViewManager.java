/* Copyright 2015 Samsung Electronics Co., LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gearvrf.webvrsample;

import java.io.IOException;
import java.util.concurrent.Future;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRCameraRig;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMaterial;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRScript;
import org.gearvrf.GVRTexture;
import org.gearvrf.scene_objects.GVRCubeSceneObject;

import android.graphics.Color;

public class SampleViewManager extends GVRScript {

    private GVRContext mGVRContext;

    private GVRScene mScene;

    private GVRMaterial mMaterial;

    @Override
    public void onInit(GVRContext gvrContext) throws IOException {

        mGVRContext = gvrContext;

        mScene = gvrContext.getNextMainScene();

        GVRCameraRig mainCameraRig = mScene.getMainCameraRig();
        int color = Color.rgb((int)(0.1f * 255), (int)(0.2f * 255), (int)(0.3f * 255));
        mainCameraRig.getLeftCamera().setBackgroundColor(color);
        mainCameraRig.getRightCamera().setBackgroundColor(color);

        Future<GVRTexture> futureTexture = gvrContext
                .loadFutureTexture(new GVRAndroidResource(gvrContext,
                        R.drawable.cube_sea));

        mMaterial = new GVRMaterial(gvrContext);
        mMaterial.setMainTexture(futureTexture);

        int gridSize = 10;
        float size = 0.4f;

        for (int x = 0; x < gridSize; ++x) {
            for (int y = 0; y < gridSize; ++y) {
                for (int z = 0; z < gridSize; ++z) {
                    if (x == 0 && y == 0 && z == 0)
                        continue;
                    GVRSceneObject cubeObject = new GVRCubeSceneObject(mGVRContext, true, mMaterial);
                    cubeObject.getTransform().setScale(size, size, size);
                    cubeObject.getTransform().setPosition(x - (gridSize/2),y - (gridSize/2), z - (gridSize/2));
                    mScene.addSceneObject(cubeObject);
                }
            }
        }

    }

    @Override
    public void onStep() {
    }

}
