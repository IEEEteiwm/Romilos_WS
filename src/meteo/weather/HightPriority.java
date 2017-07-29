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
public class HightPriority {

    public void setWind_Direction(int Wind_Direction) {
        this.Wind_Direction = Wind_Direction;
    }

    public void setWind_Speed(float Wind_Speed) {
        this.Wind_Speed = Wind_Speed;
    }

    public int getWind_Direction() {
        return Wind_Direction;
    }

    public float getWind_Speed() {
        return Wind_Speed;
    }
    
    
    
    
    private int Wind_Direction;
    private float Wind_Speed;
    
}
