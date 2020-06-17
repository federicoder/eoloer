import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { IndirizzoPersonaComponent } from 'app/entities/indirizzo-persona/indirizzo-persona.component';
import { IndirizzoPersonaService } from 'app/entities/indirizzo-persona/indirizzo-persona.service';
import { IndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';

describe('Component Tests', () => {
  describe('IndirizzoPersona Management Component', () => {
    let comp: IndirizzoPersonaComponent;
    let fixture: ComponentFixture<IndirizzoPersonaComponent>;
    let service: IndirizzoPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [IndirizzoPersonaComponent],
      })
        .overrideTemplate(IndirizzoPersonaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IndirizzoPersonaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IndirizzoPersonaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new IndirizzoPersona(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.indirizzoPersonas && comp.indirizzoPersonas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
