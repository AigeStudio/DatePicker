/*
 Copyright 2014-2015 AigeStudio(https://github.com/AigeStudio)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package cn.aigestudio.datepicker.entities;

/**
 * 英文的默认实现类
 * 如果你想实现更多的语言请参考Language{@link cn.aigestudio.datepicker.entities.Language}
 * <p/>
 * The implementation class of english.
 * You can refer to Language{@link cn.aigestudio.datepicker.entities.Language} if you want to
 * define more language.
 *
 * @author AigeStudio https://github.com/AigeStudio
 * @version 1.0.0 beta
 * @since 2015/3/26
 */
public class EN extends Language {
    @Override
    public String[] monthTitles() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    @Override
    public String ensureTitle() {
        return "Ok";
    }
}
