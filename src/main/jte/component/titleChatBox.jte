@param String name

<div class="flex items-center w-full" hx-swap-oob="true" id="title-chat-box">
    <h1 class="text-2xl font-bold text-white ml-3 cursor-pointer" id="header-title"
        onclick="window.location.href='/'">Kotlin GPT</h1>
    @if (org.springframework.util.StringUtils.hasText(name))
        <svg class="mx-3 w-4 h-4 text-gray-800 dark:text-white" aria-hidden="true" fill="none" viewBox="0 0 8 14">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="m1 13 5.7-5.326a.909.909 0 0 0 0-1.348L1 1"></path>
        </svg>
        <h2 class="text-xl font-bold text-white">${name}</h2>
    @endif

</div>

