import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { PersonaFisicaComponent } from 'app/entities/persona-fisica/persona-fisica.component';
import { PersonaFisicaService } from 'app/entities/persona-fisica/persona-fisica.service';
import { PersonaFisica } from 'app/shared/model/persona-fisica.model';

describe('Component Tests', () => {
  describe('PersonaFisica Management Component', () => {
    let comp: PersonaFisicaComponent;
    let fixture: ComponentFixture<PersonaFisicaComponent>;
    let service: PersonaFisicaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PersonaFisicaComponent],
      })
        .overrideTemplate(PersonaFisicaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonaFisicaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonaFisicaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PersonaFisica(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.personaFisicas && comp.personaFisicas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
