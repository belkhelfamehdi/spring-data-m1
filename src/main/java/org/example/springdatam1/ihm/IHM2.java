package org.example.springdatam1.ihm;

import org.example.springdatam1.entity.Author;
import org.example.springdatam1.entity.Book;
import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.entity.Publisher;
import org.example.springdatam1.services.AuthorService;
import org.example.springdatam1.services.BookService;
import org.example.springdatam1.services.GenreService;
import org.example.springdatam1.services.PublisherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class IHM2 implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);

    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final BookService bookService;

    public IHM2(AuthorService authorService, GenreService genreService, PublisherService publisherService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        while (true) {
            System.out.println("\nMenu principal :");
            System.out.println("1. Gestion des auteurs");
            System.out.println("2. Gestion des genres");
            System.out.println("3. Gestion des publishers");
            System.out.println("4. Gestion des livres");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante

            switch (choix) {
                case 1 -> gestionAuteurs();
                case 2 -> gestionGenres();
                case 3 -> gestionPublishers();
                case 4 -> gestionLivres();
                case 0 -> {
                    System.out.println("Au revoir !");
                    System.exit(0);
                }
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    // ========================== GESTION DES LIVRES ===========================
    private void gestionLivres() {
        while (true) {
            System.out.println("\nMenu gestion des livres :");
            System.out.println("1. Afficher tous les livres");
            System.out.println("2. Ajouter un livre");
            System.out.println("3. Modifier un livre");
            System.out.println("4. Supprimer un livre");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> listerLivres();
                case 2 -> ajouterLivre();
                case 3 -> modifierLivre();
                case 4 -> supprimerLivre();
                case 0 -> { return; }
                default -> System.out.println("Choix invalide. Réessayez.");
            }
            }

        }

    private void listerLivres() {
        List<Book> books = bookService.findAll();
        if (books.isEmpty()) System.out.println("Aucun livre trouvé.");
        else books.forEach(System.out::println);
    }

    private void ajouterLivre() {
        Book book = new Book();
        System.out.print("Titre du livre : ");
        book.setTitle(scanner.nextLine());

        listerAuteurs();
        System.out.print("ID de l'auteur : ");
        Long authorId = scanner.nextLong();
        scanner.nextLine();
        authorService.findById(authorId).ifPresent(book::setAuthor);

        listerGenres();
        System.out.print("ID du genre : ");
        Long genreId = scanner.nextLong();
        scanner.nextLine();
        genreService.findById(genreId).ifPresent(book.getGenres()::add);

        listerPublishers();
        System.out.print("ID du publisher : ");
        Long publisherId = scanner.nextLong();
        scanner.nextLine();
        publisherService.findById(publisherId).ifPresent(book::setPublisher);

        bookService.save(book);
        System.out.println("Livre ajouté !");
    }

    private void modifierLivre() {
        listerLivres();
        System.out.print("ID du livre à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isEmpty()) {
            System.out.println("Livre non trouvé.");
            return;
        }
        Book book = optionalBook.get();

        System.out.print("Nouveau titre : ");
        book.setTitle(scanner.nextLine());

        listerAuteurs();
        System.out.print("ID de l'auteur : ");
        Long authorId = scanner.nextLong();
        scanner.nextLine();
        authorService.findById(authorId).ifPresent(book::setAuthor);

        listerGenres();
        System.out.print("ID du genre : ");
        Long genreId = scanner.nextLong();
        scanner.nextLine();
        genreService.findById(genreId).ifPresent(book.getGenres()::add);

        listerPublishers();
        System.out.print("ID du publisher : ");
        Long publisherId = scanner.nextLong();
        scanner.nextLine();
        publisherService.findById(publisherId).ifPresent(book::setPublisher);

        bookService.save(book);
        System.out.println("Livre modifié !");
    }

    private void supprimerLivre() {
        listerLivres();
        System.out.print("ID du livre à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        bookService.delete(id);
        System.out.println("Livre supprimé !");
    }

    // ========================== GESTION DES AUTEURS ==========================
    private void gestionAuteurs() {
        while (true) {
            System.out.println("\nGestion des auteurs :");
            System.out.println("1. Ajouter un auteur");
            System.out.println("2. Lister les auteurs");
            System.out.println("3. Modifier un auteur");
            System.out.println("4. Supprimer un auteur");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterAuteur();
                case 2 -> listerAuteurs();
                case 3 -> modifierAuteur();
                case 4 -> supprimerAuteur();
                case 0 -> { return; }
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    private void ajouterAuteur() {
        System.out.print("Nom de l'auteur : ");
        String name = scanner.nextLine();
        Author author = new Author();
        author.setName(name);
        authorService.save(author);
        System.out.println("Auteur ajouté !");
    }

    private void listerAuteurs() {
        List<Author> authors = authorService.findAll();
        if (authors.isEmpty()) System.out.println("Aucun auteur trouvé.");
        else authors.forEach(a -> System.out.println(a.getId() + ". " + a.getName()));
    }

    private void modifierAuteur() {
        listerAuteurs();
        System.out.print("ID de l'auteur à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Nouveau nom : ");
        String newName = scanner.nextLine();
    
        Author newAuthorData = new Author();
        newAuthorData.setName(newName);
    
        try {
            authorService.update(id, newAuthorData);
            System.out.println("Auteur modifié !");
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void supprimerAuteur() {
        listerAuteurs();
        System.out.print("ID de l'auteur à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        authorService.delete(id);
        System.out.println("Auteur supprimé !");
    }

    // ========================== GESTION DES GENRES ==========================
    private void gestionGenres() {
        while (true) {
            System.out.println("\nGestion des genres :");
            System.out.println("1. Ajouter un genre");
            System.out.println("2. Lister les genres");
            System.out.println("3. Modifier un genre");
            System.out.println("4. Supprimer un genre");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterGenre();
                case 2 -> listerGenres();
                case 3 -> modifierGenre();
                case 4 -> supprimerGenre();
                case 0 -> { return; }
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    private void ajouterGenre() {
        System.out.print("Nom du genre : ");
        String name = scanner.nextLine();
        Genre genre = new Genre();
        genre.setName(name);
        genreService.save(genre);
        System.out.println("Genre ajouté !");
    }

    private void listerGenres() {
        List<Genre> genres = genreService.findAll();
        if (genres.isEmpty()) System.out.println("Aucun genre trouvé.");
        else genres.forEach(g -> System.out.println(g.getId() + ". " + g.getName()));
    }

    private void modifierGenre() {
        listerGenres();
        System.out.print("ID du genre à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Nouveau nom : ");
        String newName = scanner.nextLine();
    
        Genre newGenreData = new Genre();
        newGenreData.setName(newName);
    
        try {
            genreService.update(id, newGenreData);
            System.out.println("Genre modifié !");
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    
    private void supprimerGenre() {
        listerGenres();
        System.out.print("ID du genre à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        genreService.delete(id);
        System.out.println("Genre supprimé !");
    }

    // ========================== GESTION DES PUBLISHERS ==========================
    private void gestionPublishers() {
        while (true) {
            System.out.println("\nGestion des publishers :");
            System.out.println("1. Ajouter un publisher");
            System.out.println("2. Lister les publishers");
            System.out.println("3. Modifier un publisher");
            System.out.println("4. Supprimer un publisher");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterPublisher();
                case 2 -> listerPublishers();
                case 3 -> modifierPublisher();
                case 4 -> supprimerPublisher();
                case 0 -> { return; }
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    private void ajouterPublisher() {
        System.out.print("Nom du publisher : ");
        String name = scanner.nextLine();
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherService.save(publisher);
        System.out.println("Publisher ajouté !");
    }

    private void listerPublishers() {
        List<Publisher> publishers = publisherService.findAll();
        if (publishers.isEmpty()) System.out.println("Aucun publisher trouvé.");
        else publishers.forEach(p -> System.out.println(p.getId() + ". " + p.getName()));
    }

    private void modifierPublisher() {
        listerPublishers();
        System.out.print("ID du publisher à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Nouveau nom : ");
        String newName = scanner.nextLine();
    
        Publisher newPublisherData = new Publisher();
        newPublisherData.setName(newName);
    
        try {
            publisherService.update(id, newPublisherData);
            System.out.println("Publisher modifié !");
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void supprimerPublisher() {
        listerPublishers();
        System.out.print("ID du publisher à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        publisherService.delete(id);
        System.out.println("Publisher supprimé !");
    }
}
