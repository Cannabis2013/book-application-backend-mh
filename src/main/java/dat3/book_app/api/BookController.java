package dat3.book_app.api;

import dat3.book_app.dto.books.BookDetailsResponse;
import dat3.book_app.dto.books.recommendations.BookRecommendationResponse;
import dat3.book_app.entity.books.GoogleBook;
import dat3.book_app.repository.BooklistRepository;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.book_app.service.openAI.AIBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final IGoogleBooksApi googleBooks;
    private final AIBookService aiBookService;

    private final BooklistRepository booklistRepository;

    public BookController(IGoogleBooksApi googleBooks, AIBookService aiBookService, BooklistRepository booklistRepository) {
        this.googleBooks = googleBooks;
        this.aiBookService = aiBookService;
        this.booklistRepository = booklistRepository;
    }

    @GetMapping("author")
    public List<GoogleBook> books(String author){
        return googleBooks.getBooksByAuthor(author);
    }

    @GetMapping("reference")
    public BookDetailsResponse book(String reference){
        return googleBooks.getBookByReference(reference);
    }

    @GetMapping("search")
    public List<GoogleBook> searchedBooks(@RequestParam String query) {
        return googleBooks.bySearch(query);
    }

    @GetMapping("slice")
    public List<GoogleBook> slicedBooks(@RequestParam Optional<String> genre) {
        if (genre.isPresent())
            return googleBooks.sliceWithGenre(genre.get());
        return googleBooks.slice();
    }
    @GetMapping("recommendations")
    public List<BookRecommendationResponse> recommended(String author, String title){
        var aiResponse = aiBookService.recommendations(author,title,5);
        return googleBooks.fromAiRecommendations(aiResponse);
    }
}
