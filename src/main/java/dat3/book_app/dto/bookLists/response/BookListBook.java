package dat3.book_app.dto.bookLists.response;

import dat3.book_app.entity.books.GoogleBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
public class BookListBook {
    public BookListBook(GoogleBook googleBook) {
        id = googleBook.getId();
        title = googleBook.getVolumeInfo().getTitle();
        authors = googleBook.getVolumeInfo().getAuthors();
        categories = googleBook.getVolumeInfo().getCategories();
        priceAmount = googleBook.getSaleInfo().getRetailPrice().getAmount();
        currency = googleBook.getSaleInfo().getRetailPrice().getCurrencyCode();
        image = googleBook.getVolumeInfo().getImageLinks().getThumbnail();
    }

    private String id;
    private String title;
    private ArrayList<String> authors;
    private ArrayList<String> categories;
    private double priceAmount;
    private String currency;
    private String image = "";
}
