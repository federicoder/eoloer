import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { EmailPersonaDetailComponent } from 'app/entities/email-persona/email-persona-detail.component';
import { EmailPersona } from 'app/shared/model/email-persona.model';

describe('Component Tests', () => {
  describe('EmailPersona Management Detail Component', () => {
    let comp: EmailPersonaDetailComponent;
    let fixture: ComponentFixture<EmailPersonaDetailComponent>;
    const route = ({ data: of({ emailPersona: new EmailPersona(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [EmailPersonaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EmailPersonaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmailPersonaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load emailPersona on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.emailPersona).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
