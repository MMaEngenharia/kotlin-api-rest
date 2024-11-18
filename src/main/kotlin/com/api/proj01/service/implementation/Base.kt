package com.api.proj01.service.implementation

import com.api.proj01.model.dto.APIResponse
import com.api.proj01.model.search.Search
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class Base() {

    protected val PAGE_COUNT: Int = 10

    protected fun getPageable(search: Search): Pageable {
        return PageRequest.of(
            search.page -1,
            if (search.totalPerPage > 0) search.totalPerPage else PAGE_COUNT,
            Sort.by( /*filter.getSort().equals(SortEnum.DESC) ?
                    Sort.Direction.DESC :
                    Sort.Direction.ASC,*/
                Sort.Direction.DESC,
                "id"
            )
        )
    }

    protected fun getApiResponse(list: Page<*>): APIResponse {
        return APIResponse(
            data = list.toList(),
            page = list.number + 1,
            totalPages = list.totalPages,
            size = list.size
        )
    }
}