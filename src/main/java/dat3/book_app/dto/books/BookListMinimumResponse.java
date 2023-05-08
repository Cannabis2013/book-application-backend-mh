package dat3.book_app.dto.books;

import dat3.book_app.entity.googleBooks.Booklist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookListMinimumResponse {
    public BookListMinimumResponse(Booklist list) {
        references = list.getBookReferences();
        title = list.getTitle();
        listCount = list.getBookReferences().size();
        id = list.getId();
        createdAt = list.getCreatedAt();
        updatedAt = list.getLastEdited();
    }

    private List<String> references = new ArrayList<>();
    private String title;
    private int listCount;
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
