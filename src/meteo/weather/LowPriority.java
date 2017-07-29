/*
 * Copyright 2017 IEEE Gram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package meteo.weather;

/**
 *
 * @author IEEE Gram
 */
public class LowPriority {

    public void setPreasure(float Preasure) {
        this.Preasure = Preasure;
    }

    public void setRainIn(int RainIn) {
        this.RainIn = RainIn;
    }

    public void setHumidity(float Humidity) {
        this.Humidity = Humidity;
    }

    public float getPreasure() {
        return Preasure;
    }

    public int getRainIn() {
        return RainIn;
    }

    public float getHumidity() {
        return Humidity;
    }
    
    private float Preasure;
    private int RainIn;
    private float Humidity;
}
