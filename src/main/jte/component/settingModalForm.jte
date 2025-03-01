@import freeapp.life.freeaiweb.dto.OllamaResponseRto
@import freeapp.life.freeaiweb.dto.AIModelDto

@param OllamaResponseRto setting


<form class="" hx-put="/setting" hx-swap="none" hx-on--after-swap="closeModal()">
    <div class="p-4 md:p-5 space-y-4">
        <div class="mb-5">
            <label for="models"
                   class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Model</label><select
                    id="models" name="model"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">

                <option value="" selected="${(setting.getCurrentModel().isEmpty())}">
                    Choose a model
                </option>

                @for(AIModelDto model : setting.getModels())
                    <option value="${model.getModel()}"
                            selected="${setting.getCurrentModel().equals(model.getModel())}">${model.getModel()}
                    </option>
                @endfor

            </select>
        </div>
        <div class="mb-5">
            <label for="host" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your
                Host</label><input name="host" type="text" id="host"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                   value="${setting.getHost()}" required>
        </div>
        <div class="mb-5"><label for="temperature" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Temperature</label><input
                    id="temperature" name="temperature" type="range" value="${setting.getTemperature()}" min="0" max="1"
                    step="0.1"
                    class="w-full h-2 bg-gray-200 rounded-lg cursor-pointer dark:bg-gray-600">
            <div class="flex w-full justify-between px-2 text-xs">
                @for(int i = 0; i <= 10; ++i)
                    <span>${String.format("%.1f", i / 10.0)}</span>
                @endfor
            </div>
        </div>


        <div class="mb-5"><label for="topp" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Top
                P</label><input id="topp" type="range" name="topP" value="${setting.getTopP()}" min="0" max="1"
                                step="0.1"
                                class="w-full h-2 bg-gray-200 rounded-lg cursor-pointer dark:bg-gray-700">
            <div class="flex w-full justify-between px-2 text-xs">
                @for(int i = 0; i <= 10; ++i)
                    <span>${String.format("%.1f", i / 10.0)}</span>
                @endfor
            </div>
        </div>
        <div class="mb-5"><label for="number-input"
                                 class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Top
                K</label><input type="number" id="number-input" name="topK" max="100" min="0"
                                aria-describedby="helper-text-explanation"
                                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                value="${setting.getTopK()}" required=""></div>
    </div>
    <div class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600 ">
        <button type="submit"
                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Change
        </button>
    </div>
</form>
