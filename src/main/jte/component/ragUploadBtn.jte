<button class="flex-1 px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500
                              inline-block group text-white font-bold flex items-center justify-center"
        id="upload-btn" type="button" data-tooltip-target="tooltip-upload"
        hx-post="/upload"
        hx-target="#file-list"
        hx-swap="beforeend"
        hx-encoding="multipart/form-data"
        hx-trigger="change from:#fileInput"
<%--        hx-on--after-request="javascript:menualInitFlowbite()"  Outerhtml이라 작동 X--%>
        hx-on--after-request="event.stopPropagation(); console.log('이벤트 버블링 방지');"
        onclick="document.getElementById('fileInput').click()"
        hx-indicator="#spinner"
>
    <input type="file" id="fileInput" name="file" class="hidden">
    @template.component.loadingSpinner()
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
         stroke="currentColor" class="upload-svg">
        <path stroke-linecap="round" stroke-linejoin="round"
              d="M3 16.5v2.25A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75V16.5M16.5 12 12 16.5m0 0L7.5 12m4.5 4.5V3"/>
    </svg>
    <div id="tooltip-upload" role="tooltip" class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-xs opacity-0 tooltip dark:bg-gray-700">
        File Upload
        <div class="tooltip-arrow" data-popper-arrow></div>
    </div>
</button>
