@import freeapp.life.freeaiweb.dto.ChatRespDto
@import org.springframework.data.domain.Page
@import java.util.function.Function
@import gg.jte.Content

@param  ChatRespDto currentChat
@param Page<ChatRespDto> chats


!{Function<Page<ChatRespDto>,Content> component = (chatList) -> @`
    <ul class="space-y-2">
        @for(ChatRespDto chat : chatList.getContent())

            !{var selectedColor = (currentChat != null && chat.getId() == currentChat.getId()) ? "bg-[#b4b4b480]" : "";}

            <div class="flex truncate group cursor-pointer tab-active hover:bg-[#b4b4b480] ${selectedColor}"
                 id="chat-li-${chat.getId()}"
                 hx-on--after-on-load="let currentTab = document.querySelector('#chat-nav-list');
                        if (currentTab) {
                            currentTab.classList.remove('bg-[#b4b4b480]');
                            currentTab.querySelectorAll('.bg-\\[\\#b4b4b480\\]').forEach(el =&gt; {
                            el.classList.remove('bg-[#b4b4b480]');
                          });
                        }
                        let newTab = event.currentTarget
                        newTab.classList.add('bg-[#b4b4b480]') ">
                @template.component.chatNameBox(chat = chat)

                <div class="w-1/6 cursor-pointer opacity-0 group-hover:opacity-100 transition-opacity duration-200"
                     id="dropdown-btn-${chat.getId()}" data-dropdown-toggle="dropdown-${chat.getId()}">
                    <svg xmlns="http://www.w3.org/2000/svg" class="icon-md z-50" width="24" height="24"
                         viewbox="0 0 24 24" fill="none">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                              d="M3 12C3 10.8954 3.89543 10 5 10C6.10457 10 7 10.8954 7 12C7 13.1046 6.10457 14 5 14C3.89543 14 3 13.1046 3 12ZM10 12C10 10.8954 10.8954 10 12 10C13.1046 10 14 10.8954 14 12C14 13.1046 13.1046 14 12 14C10.8954 14 10 13.1046 10 12ZM17 12C17 10.8954 17.8954 10 19 10C20.1046 10 21 10.8954 21 12C21 13.1046 20.1046 14 19 14C17.8954 14 17 13.1046 17 12Z"
                              fill="currentColor"></path>
                    </svg>
                </div>
                <div class="z-50 hidden bg-white divide-y divide-gray-100 rounded-lg shadow-sm w-44 dark:bg-gray-700"
                     id="dropdown-${chat.getId()}">
                    <ul class="py-2 text-sm text-gray-700 dark:text-gray-200"
                        aria-labelledby="dropdown-btn-${chat.getId()}">
                        <li hx-get="/chat/rename/${chat.getId()}" hx-trigger="click"
                            hx-target="#chat-name-div-${chat.getId()}"
                            hx-swap="outerHTML" hx-ext="debug"
                            hx-vals='{"name": "${chat.getName()}" } '>
                            <span class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Rename</span>
                        </li>
                        <li hx-delete="/chat/${chat.getId()}" hx-confirm="Are you sure you wish to delete?"
                            hx-trigger="click"
                            hx-target="#chat-li-${chat.getId()}" hx-swap="delete"><span
                                    class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Delete</span>
                        </li>
                    </ul>
                </div>
            </div>
        @endfor
    </ul>
`;}



@if (!chats.isLast())
    <nav class="mt-2"
         hx-get="/chats?page=${chats.getPageable().getPageNumber() + 1}"
         hx-trigger="revealed"
         hx-on--after-request="javascript:menualInitFlowbite()"
         hx-swap="afterend">
        ${component.apply(chats)}
    </nav>
@else
    <nav class="mt-2">
        ${component.apply(chats)}
    </nav>
@endif





