import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { UserPersonaDetailComponent } from 'app/entities/user-persona/user-persona-detail.component';
import { UserPersona } from 'app/shared/model/user-persona.model';

describe('Component Tests', () => {
  describe('UserPersona Management Detail Component', () => {
    let comp: UserPersonaDetailComponent;
    let fixture: ComponentFixture<UserPersonaDetailComponent>;
    const route = ({ data: of({ userPersona: new UserPersona(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [UserPersonaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserPersonaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserPersonaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userPersona on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userPersona).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
