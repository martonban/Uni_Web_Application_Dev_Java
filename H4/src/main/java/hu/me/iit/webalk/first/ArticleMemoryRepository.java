package hu.me.iit.webalk.first;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ArticleMemoryRepository implements ArticleRepository {

    private final List<ArticleDto> articles = new ArrayList<>();

    public int findArticelByID(Long id){
        int found = -1;
        for (int i=0; i<articles.size(); i++){
            if (articles.get(i).getId()==id){
                found = i;
                break;
            }
        }
        return found;
    }


    @Override
    public List<ArticleDto> findAll() {
        return articles;
    }

    @Override
    public ArticleDto getById(Long id) {
        int articleByIdIndex = findArticelByID(id);
        return articleByIdIndex == -1 ? null : articles.get(articleByIdIndex);
    }

    @Override
    public Long save(ArticleDto articleDto) {
        int found = findArticelByID(articleDto.getId());

        if (found != -1) {
            ArticleDto foundArticle = articles.get(found);
            foundArticle.setAuthor(articleDto.getAuthor());
            foundArticle.setPages(articleDto.getPages());
            foundArticle.setTitle(articleDto.getTitle());
        } else {
            articles.add(articleDto);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        int found = findArticelByID(id);

        if (found != -1) {
            articles.remove(found);
        }
    }

}
