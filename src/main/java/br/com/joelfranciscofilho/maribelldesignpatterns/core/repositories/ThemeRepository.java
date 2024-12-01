package br.com.joelfranciscofilho.maribelldesignpatterns.core.repositories;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
