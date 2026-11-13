package team.projectpulse.ram.document;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RequirementDocumentSpecsTest {

    @Test
    void titleSearchUsesSamePatternUnderTurkishDefaultLocale() {
        Root<RequirementDocument> document = mock();
        Path<String> title = mock();
        CriteriaBuilder builder = mock();
        Expression<String> lowerTitle = mock();
        when(document.<String>get("title")).thenReturn(title);
        when(builder.lower(title)).thenReturn(lowerTitle);

        Locale previous = Locale.getDefault();
        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"));
            RequirementDocumentSpecs.hasTitleLike("TITLE").toPredicate(document, null, builder);
            verify(builder).like(lowerTitle, "%title%");
        } finally {
            Locale.setDefault(previous);
        }
    }
}
