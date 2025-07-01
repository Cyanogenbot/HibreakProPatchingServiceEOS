**/e/os** tweaks for the **Hibreak Pro**, adding eink features support
For advanced users, check xda for pre-patched images. Based on work from vbbot, Colors and AndyCGYan.

> [!NOTE]
> I do not make any security claims about this operating system as this is all bigme's and /e/os responsibility, I solely compiled the steps it took for me to build this rom myself. Due to bigme's recent badbox2 related problems I have returned my own HiBreak Pro, but for those who dare, this is all you need to get started!

## Additionally info on building this rom yourself in the future:
Get started following the steps from: https://community.e.foundation/t/e-os-u-gsi-a14-unofficial/67048/18?u=diedeboef
> [!WARNING]
> 1. In step one, dont make run the first `mkdir a14` and `cd a14` as this will only create confusion  and result into 2 a14 folders.
> 2. Then before running `repo sync -j 1 --fail-fast --force-sync` to download all of the repo’s, it is important you first remove the line locating github in the local_manifests/manifest.xml file. Because the repo sync wont work without.

### Additional steps before building:
However before building in the last step you should 
1. move the repo contents into folder named a9_services within the a14/vendor folder.
2. within a14/device/phh/treble/sepolicy add the following line at the bottom of the file_contexts file
    
    ```
       /system/bin/a9_eink_server u:object_r:a9_eink_server_exec:s0
    ```

3. Create a file named "vendorsetup.sh" in the `a14/device/phh/treble` folder and add the following content
    
    ```
    export EOS_DEVICE=HiBreak Pro
    export EOS_RELEASE_TYPE=UNOFFICIAL
    export EOS_BRANCH_NAME=a14
    export EOS_SIGNATURE_SPOOFING=restricted
    ```
4. Change the content of a14/device/phh/treble/treble_arm64_bvN.mk to match the treble_arm64_bvN.mk file in this repo.
5. Add the following lines to the bottom of the a14/vendor/lineage/overlay/common/frameworks/base/core/res/res/values/config.xml file
   ```
   <!-- Component name for the activity that will be presenting the Recents UI, which will receive
     special permissions for API related to fetching and presenting recent tasks. The default
     configuration uses Launcehr3QuickStep as default launcher and points to the corresponding
     recents component. When using a different default launcher, change this appropriately or
     use the default systemui implementation: com.android.systemui/.recents.RecentsActivity -->
    <string name="config_recentsComponentName" translatable="false">foundation.e.blisslauncher/com.android.quickstep.RecentsActivity</string>

    <!-- This is the default launcher package with an activity to use on secondary displays that
         support system decorations.
         This launcher package must have an activity that supports multiple instances and has
         corresponding launch mode set in AndroidManifest.
         {@see android.view.Display#FLAG_SHOULD_SHOW_SYSTEM_DECORATIONS} -->
    <string name="config_secondaryHomePackage" translatable="false">foundation.e.blisslauncher</string>```
6. Build! Your first build might take ages so enjoy the wait.
7. Flash and enjoy your new privacy friendly rom

## Licensing

### MIT License
Most of the project is licensed under the MIT License unless specified otherwise

The code and releases are provided “as is,” without any express or implied warranty of any kind including warranties of merchantability, non-infringement, title, or fitness for a particular purpose.

### Apache License 2.0
The file `HardwareGestureDetector.kt` includes code derived from AOSP. The original code is subject to the following license:

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Modifications and additions to the original code are licensed under the MIT License
