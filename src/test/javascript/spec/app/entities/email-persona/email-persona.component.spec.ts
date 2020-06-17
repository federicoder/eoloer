import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { EmailPersonaComponent } from 'app/entities/email-persona/email-persona.component';
import { EmailPersonaService } from 'app/entities/email-persona/email-persona.service';
import { EmailPersona } from 'app/shared/model/email-persona.model';

describe('Component Tests', () => {
  describe('EmailPersona Management Component', () => {
    let comp: EmailPersonaComponent;
    let fixture: ComponentFixture<EmailPersonaComponent>;
    let service: EmailPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [EmailPersonaComponent],
      })
        .overrideTemplate(EmailPersonaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmailPersonaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmailPersonaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EmailPersona(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.emailPersonas && comp.emailPersonas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
